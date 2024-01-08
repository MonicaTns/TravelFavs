package com.tanase.travelfavs

fun convertDatePickerParamsToDate(year: Int, month: Int, day: Int): String = "$day.${month.plus(1)}.$year"