package com.helloworld.androiddemo.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.helloworld.androiddemo.Permissions
import com.helloworld.androiddemo.R
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity()
{
    private var selectedProvider = LocationManager.GPS_PROVIDER
    private lateinit var locationManager: LocationManager
    private lateinit var permissions: Permissions

    @SuppressLint("MissingPermission")
    private fun updateLocation()
    {
        val location = locationManager.getLastKnownLocation(selectedProvider)
        val info = """
                    Longitude: ${location?.longitude ?: "Unknown"}
                    Latitude: ${location?.latitude ?: "Unknown"}
                """.trimIndent()
        textLocation.text = info
    }
    @SuppressLint("MissingPermission")
    private fun updateLocationListener()
    {
        locationManager.requestLocationUpdates(selectedProvider, 500, 0f, object : LocationListener
        {
            override fun onProviderDisabled(p0: String?)
            {
            }
            override fun onProviderEnabled(p0: String?)
            {
            }
            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?)
            {
            }
            override fun onLocationChanged(location: Location?)
            {
                updateLocation()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        permissions = Permissions(this)
        permissions.request(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val availableProviders = locationManager.getProviders(true)
            val locationProviders = mapOf<String, String>(
                "GPS" to LocationManager.GPS_PROVIDER,
                "Network" to LocationManager.NETWORK_PROVIDER,
                "Passive" to LocationManager.PASSIVE_PROVIDER
            ).filter {
                availableProviders.contains(it.value)
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationProviders.keys.toTypedArray())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, index: Int, l: Long)
                {
                    selectedProvider = locationProviders.values.elementAt(index)
                    updateLocationListener()
                }

                override fun onNothingSelected(p0: AdapterView<*>?)
                {
                    selectedProvider = locationProviders.values.first()
                    updateLocationListener()
                }
            }
            buttonGetLocation.setOnClickListener {
                updateLocation()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        this.permissions.callback(requestCode, permissions, grantResults)
    }
}
