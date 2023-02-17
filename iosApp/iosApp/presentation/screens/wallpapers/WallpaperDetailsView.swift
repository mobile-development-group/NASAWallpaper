//
//  WallpaperDetailsView.swift
//  iosApp
//
//  Created by Pavlo Kravchenko on 17.02.2023.
//  Copyright © 2023 mdgroup. All rights reserved.
//

import SwiftUI

struct WallpaperDetailsView: View {
    
    let item: WallpaperIdentifiable
    let onClickSave: () -> ()
    let onClickShare: () -> ()
    let onClickAsWallpaper: () -> ()
    
    var body: some View {
        ScrollView {
            VStack {
                if let url = item.uri ?? item.url {
                    AsyncImage(
                        url: URL(string: url),
                        content: { image in
                            image.resizable()
                        },
                        placeholder: {
                            ProgressView()
                        }
                    )
                    .frame(height: 400)
                }
                
                VStack(alignment: .leading) {
                    Text(item.title)
                        .font(.system(size: 20))
                        .padding(.top, 16)
                        .padding(.horizontal, 16)
                    
                    Text(item.date)
                        .padding(.horizontal, 16)
                    
                    HStack {
                        Button(action: onClickSave, label: {
                            Image(systemName: "bookmark")
                        })
                        Button(action: onClickShare, label: {
                            Image(systemName: "square.and.arrow.up")
                        })
                        .padding(.leading, 8)
                        Button(action: onClickAsWallpaper, label: {
                            Image(systemName: "arrow.turn.up.forward.iphone.fill")
                        })
                        .padding(.leading, 8)
                    }
                    .padding(.top, 8)
                    .padding(.horizontal, 16)
                    
                    if let explanation = item.explanation {
                        Text(explanation)
                            .padding(.top, 16)
                            .padding(.horizontal, 16)
                    }
                    
                    if let copyright = item.copyright {
                        Text(copyright)
                            .padding(.top, 8)
                            .padding(.horizontal, 16)
                    }
                }
            }
        }
    }
}

struct WallpaperDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        WallpaperDetailsView(
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
            ),
            onClickSave: {},
            onClickShare: {},
            onClickAsWallpaper: {}
        )
    }
}
