package com.dezzy.gceresults.ui.screens.search.models

sealed class SearchField(val key: String, val label: String) {
    class CenterNumberField(key: String = "centerNumber") : SearchField(key, "Enter Center Number")
    class CenterNameField(key: String = "centerName") : SearchField(key, "Enter Center Name")
    class NameField(key: String = "name") : SearchField(key, "Enter Name")
    class YearField(key: String = "year") : SearchField(key, "Enter Year")
}
