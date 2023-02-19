//
//  NavigationBarColorModifier.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 01.04.2022.
//  Copyright © 2022 mdgroup. All rights reserved.
//

import Foundation
import SwiftUI

struct NavigationBarColorModifier<Background>: ViewModifier where Background: View {
    let background: () -> Background
    public init(@ViewBuilder background: @escaping () -> Background) {
        self.background = background
    }

    func body(content: Content) -> some View {
        ZStack {
            content
            VStack {
                background()
                    .edgesIgnoringSafeArea([.top, .leading, .trailing])
                    .frame(minWidth: 0, maxWidth: .infinity, maxHeight: 0, alignment: .center)

                Spacer() // to move the navigation bar to top
            }
        }
    }
}

public extension View {
    func navigationBarBackground<Background: View>(@ViewBuilder _ background: @escaping () -> Background) -> some View {
        modifier(NavigationBarColorModifier(background: background))
    }
}
