//
//  WallpaperScreen.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI

struct WallpaperScreen: View {
    
    @StateObject
    var viewModel: WallpaperViewModel
    
    init(item: WallpaperIdentifiable) {
        self._viewModel = StateObject(wrappedValue: getWallpaperViewModel(item: item))
    }
    
    var body: some View {
        if let wallpaper = viewModel.wallpaper {
            WallpaperDetailsView(
                item: wallpaper,
                onClickBookmark: { viewModel.toBookmark() }
            )
            .ignoresSafeArea()
        } else {
            ProgressView()
        }
    }
}

struct WallpaperScreen_Previews: PreviewProvider {
    static var previews: some View {
        WallpaperScreen(
            item: WallpaperIdentifiable(
                copyright: "Gijs de Reijke",
                date: "2023-02-17",
                explanation: """
While scanning the skies for near earth objects Hungarian astronomer Krisztin Srneczky first imaged the meter-sized space rock now cataloged as 2023 CX1 on 12 February 2023 at 20:18:07 UTC. That was about 7 hours before it impacted planet Earth's atmosphere. Its predicted trajectory created a rare opportunity for meteor observers and a last minute plan resulted in this spectacular image of the fireball, captured from the Netherlands as 2023 CX1 vaporized and broke up over northern France. Remarkably it was Srneczky's second discovery of an impacting asteroid, while 2023 CX1 is only the seventh asteroid to be detected before being successfully predicted to impact Earth. It has recently become the third such object from which meteorites have been recovered. This fireball was witnessed almost 10 years to the day following the infamous Chelyabinsk Meteor flash.
""",
                hdurl: "https://apod.nasa.gov/apod/image/2302/gijsDSC_1917(2x3)1600px.jpg",
                mediaType: "image",
                serviceVersion: "v1",
                title: "2023 CX1 Meteor Flash",
                url: "https://apod.nasa.gov/apod/image/2302/gijsDSC_1917(2x3)800px.jpg"
            )
        )
    }
}
