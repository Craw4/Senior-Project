package edu.hanover.hc24_luuk_crawford_senior_project.data

import java.util.Date

data class UserOrder(var user: String,
                     var userID: Int,
                     val orderID: Int,
                     var itemID: Int,
                     var customization: Customization,
                     var itemStatus: ItemStatus,
                     var orderTime: Long,
                     var orderEndTime: Long)