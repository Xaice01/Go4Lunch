package com.xavier_carpentier.go4lunch.presentation.ui.map_restaurant;

import static com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity.KEY_RESTAURANT;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.xavier_carpentier.go4lunch.R;
import com.xavier_carpentier.go4lunch.presentation.model.RestaurantItem;
import com.xavier_carpentier.go4lunch.presentation.ui.detail_restaurant.DetailRestaurantActivity;
import com.xavier_carpentier.go4lunch.presentation.viewmodel.MapViewModel;

import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback, OnMarkerClickListener {

    private GoogleMap mMap;
    private MapViewModel mapViewModel;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
        mapViewModel.startLocationUpdates();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewModel.stopLocationUpdates();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            return;
        }

        mMap.setMyLocationEnabled(true);

        mapViewModel.getLocationLiveData().observe(getViewLifecycleOwner(), locationUi -> {
            LatLng userLocation = new LatLng(Double.parseDouble(locationUi.getLatitude()), Double.parseDouble(locationUi.getLongitude()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));

            mapViewModel.getRestaurantsLiveData(locationUi.getLatitude(), locationUi.getLongitude()).observe(getViewLifecycleOwner(), this::addMarkers);
            googleMap.setOnMarkerClickListener(marker -> {
                if (marker.getTitle()!=null) {
                    onMarkerClick(marker.getTitle());
                }
                return false;
            });
        });
    }

    private void addMarkers(List<RestaurantItem> restaurantItems) {
        mMap.clear();
        for (RestaurantItem restaurant : restaurantItems) {
            LatLng restaurantLocation = new LatLng(Double.parseDouble(restaurant.getLatitude()), Double.parseDouble(restaurant.getLongitude()));
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(restaurantLocation)
                    .title(restaurant.getUid());

            if (restaurant.getWorkmatesToEat() > 0) {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            } else {
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }


            mMap.addMarker(markerOptions);
        }
    }

    @Override
    public void onMarkerClick(@NonNull String restaurantId) {
        Intent intent = new Intent(getActivity(), DetailRestaurantActivity.class);
        intent.putExtra(KEY_RESTAURANT, restaurantId);
        startActivity(intent);
    }
}