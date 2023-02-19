//
//  SettingsScreen.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI
import MessageUI
import StoreKit

struct SettingsScreen: View {
    
    @Binding
    var tabSelection: Tab
    
    @State
    private var result: Result<MFMailComposeResult, Error>? = nil
    
    @State
    private var isShowMailAlert = false
    
    @State
    private var isShowMailSheet = false
    
    var body: some View {
        let version = "\("version".localized()): \(Bundle.main.version) (\(Bundle.main.versionCode))\(Bundle.main.isDebug ? " - DEBUG" : "")"
        
        NavigationView {
            Form {
                Section(
                    header: Text("other".localized()),
                    footer: Text(version)
                ) {
                    FormText(title: "report_error".localized())
                        .onTapGesture {
                            self.sendFeedbackViaMail()
                        }
                    FormText(title: "rate_app".localized())
                        .onTapGesture { onClickRateApp() }
                    NavigationLink(destination: AboutScreen()){
                        Text("about_app".localized())
                    }
                    NavigationLink(destination: MoreAppsScreen()){
                        Text("more_apps".localized())
                    }
                }
            }
            .navigationTitle("settings".localized())
            .navigationBarTitleDisplayMode(.inline)
        }
        .navigationViewStyle(.stack)
        .sheet(isPresented: $isShowMailSheet) {
            self.makeMailView()
        }
        .alert(isPresented: $isShowMailAlert, content: {
            Alert(
                title: Text("error".localized()),
                message: Text("cant_send_email".localized()))
        })
    }
    
    func sendFeedbackViaMail() {
        if MFMailComposeViewController.canSendMail() {
            self.isShowMailSheet.toggle()
        } else {
            self.isShowMailAlert.toggle()
        }
    }
    
    func makeMailView() -> MailView {
        let subject = "\(Bundle.main.displayName) - \(Bundle.main.version)(\(Bundle.main.versionCode))"
        
        return MailView(
            result: self.$result,
            toRecipients: ["developer_email".localized()],
            subject: subject
        )
    }
    
    func onClickRateApp() {
        if let scene = UIApplication.shared.connectedScenes
                .first(where: { $0.activationState == .foregroundActive }) as? UIWindowScene {
            SKStoreReviewController.requestReview(in: scene)
        } else {
            if let url = URL(string: "itms-apps://itunes.apple.com/app/\(Constants.APP_ID)") {
                UIApplication.shared.open(url)
            }
        }
    }
}

struct SettingsScreen_Previews: PreviewProvider {
    static var previews: some View {
        SettingsScreen(tabSelection: .constant(Tab.settings))
    }
}
