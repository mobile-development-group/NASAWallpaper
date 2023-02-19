//
//  FormItem.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 19.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI

/// I used additional HStack, Spacer and Rectangle so that the onTapGesture method works on the entire element zone and not just on the text
struct FormText: View {
    
    var title: String
    
    var body: some View {
        HStack {
            Text(title)
            Spacer()
        }
        .contentShape(Rectangle())
        .frame(maxWidth: .infinity)
        .animation(
            Animation
                .linear
                .repeatForever(autoreverses: false),
            value: 1
        )
    }
}

struct FormText_Previews: PreviewProvider {
    static var previews: some View {
        FormText(title: "Click on me")
            .onTapGesture { }
    }
}

