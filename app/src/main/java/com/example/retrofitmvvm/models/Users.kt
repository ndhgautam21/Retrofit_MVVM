package com.example.retrofitmvvm.models

data class Users(
    val id : Int?,
    val name : String,
    val email_id : String,
    val phone_no : String,
    val image : String,
    val created_at : String?,
    val updated_at : String?
)