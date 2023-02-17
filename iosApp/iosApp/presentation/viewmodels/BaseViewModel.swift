//
//  BaseViewModel.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import Foundation

class BaseViewModel : ObservableObject {
    
    @Published
    var isLoading = true
    
    func showLoading(){
        isLoading = true
    }
    
    func hideLoading(){
        isLoading = false
    }
}
