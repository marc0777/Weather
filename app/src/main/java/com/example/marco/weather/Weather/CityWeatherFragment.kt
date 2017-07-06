package com.example.marco.weather.Weather

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.marco.weather.R

class CityWeatherFragment : Fragment() {
    private var viewModel = WeatherViewModel()
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments.getInt("position")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
        val textView = view as TextView
        viewModel.setWeather(position, textView)
        return view
    }

    companion object {
        fun newInstance(position: Int): CityWeatherFragment {
            val args = Bundle()
            args.putInt("position", position)
            val fragment = CityWeatherFragment()
            fragment.arguments = args
            return fragment
        }
    }
}