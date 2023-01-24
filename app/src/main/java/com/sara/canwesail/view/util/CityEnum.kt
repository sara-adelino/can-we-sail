package com.sara.canwesail.view.util

import com.sara.canwesail.R

enum class CityEnum (
    var cityId: String,
    var cityBackgroundId: Int,
    var cityBackgroundUrl: String = ""
    ) {
    BERLIN(
        "berlin",
        R.drawable.berlin,
        "https://i.pinimg.com/564x/f9/78/98/f97898e09c2169e196565871f5f84e3f.jpg"
    ),
    COPENHAGEN(
        "copenhagen",
        R.drawable.copenhagen,
        "https://i.pinimg.com/736x/5a/99/f4/5a99f40825bde2ef43cd2913dead9a76.jpg"
    ),
    DUBLIN(
        "dublin",
        R.drawable.dublin,
        "https://i.pinimg.com/474x/1c/54/5c/1c545ce844c593e3c39db9a51806ea9c--irish-eyes-dublin-ireland.jpg"
    ),
    LISBON(
        "lisbon",
        R.drawable.lisbon,
        "https://i.pinimg.com/736x/a2/0a/1d/a20a1d42b3ae6e3610a9de6b9ac8f00e.jpg"
    ),
    LONDON(
        "london",
        R.drawable.london,
        "https://i.pinimg.com/736x/db/ce/44/dbce442d7e310c0b82f4db5bb0434d0a.jpg"
    ),
    MADRID(
        "madrid",
        R.drawable.madrid,
        "https://i.pinimg.com/originals/a7/f0/20/a7f02048503b4a8fb5bef5ba40dfa5fe.jpg"
    ),
    PARIS(
        "paris",
        R.drawable.paris,
        "https://i.pinimg.com/736x/4f/d1/a2/4fd1a275bccec04cd9927717a775b415.jpg"
    ),
    PRAGUE(
        "prague",
        R.drawable.prague,
        "https://i.pinimg.com/736x/09/d1/38/09d138513d74c7ae7cedc902b3febe6d.jpg"
    ),
    ROME(
        "rome",
        R.drawable.rome,
        "https://i.pinimg.com/736x/42/16/24/42162414a7983208c45a684fc947e505.jpg"
    ),
    VIENNA(
        "vienna",
        R.drawable.vienna,
        "https://i.pinimg.com/736x/47/8f/9d/478f9d8d761108ea0885407de4c069f7.jpg"
    )
}

fun getCityBackgroundImage (cityIdentification: String): Int {
    return CityEnum.values().firstOrNull{it.cityId == cityIdentification.lowercase()}?.cityBackgroundId
        ?: R.drawable.city
}

fun getCityBackgroundUrl (cityIdentification: String): String {
    return CityEnum.values().firstOrNull{it.cityId == cityIdentification.lowercase()}?.cityBackgroundUrl
        ?: ""
}