//
//  MoreAppsViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 18.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation
import UIKit

class MoreAppsViewModel : BaseViewModel {
    
    @Published
    var appIdentifiables: [MoreAppIdentifiable] = []
    
    override init() {
        super.init()
        self.fetch()
    }
    
    func fetch(){
        appIdentifiables = [
            MoreAppIdentifiable(
                name: "Terminal & SSH",
                description: "Terminal & SSH is a complete command line and server administration solution that provides secure access to Linux or IoT devices and helps you quickly resolve issues from a mobile device.",
                appId: "id1558156247",
                imageRes: "TerminalSSH"
            ),
            MoreAppIdentifiable(
                name: "AdStat",
                description: """
                The application is intended for developers or advertisers.
                This is a safe and secure way to view your AdSense and AdMob earnings. It uses Google's secure login and does not save your password or any personal information.
                You can perform simple data analysis and view it in simple reports and charts.

                Functions
                – Supported data sources from the AdMob Reports API.
                - Earnings reports for today, yesterday, this week, last month and for any selected period
                - Application and banner reports
                - Income charts
                - Dark mode
                - Different currencies
                """,
                appId: "id1618596902",
                imageRes: "AdStat"
            ),
            MoreAppIdentifiable(
                name: "Firebase Pusher",
                description: """
                Functions:
                - Sending notifications to a specific user by firebase id
                - Sending notifications to a group of users
                - Sending notifications using the conditions
                - Get response after notification
                - Able to send custom notifications via JSON
                - You can save the list of applications
                - Saves the history of sent notifications
                - Reading documentation about notification
                """,
                appId: "id1500150434",
                imageRes: "FirebasePusher"
            )
        ]
    }
    
    func selectItem(index: Int){
        let link = "https://apps.apple.com/app/\(appIdentifiables[index].appId)"
        if let url = URL(string: link) {
            UIApplication.shared.open(url)
        }
    }
}
