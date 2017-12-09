//
//  UnsplashAuthenticationService.swift
//  ImageViewer
//
//  Created by Zakhar Sukhanov on 2017-12-08.
//  Copyright © 2017 Zakhar Sukhanov. All rights reserved.
//

import UIKit
import Alamofire
import PromiseKit

class UnsplashAuthenticationService: NSObject {
  override init() {
    //
  }
  
  func authorize(applicationID: String) -> Promise<Void> {
    
    return Promise { fulfill, reject in
      
      guard let authUrl = URL(string: Settings.shared.authorizationURL) else {
        let msg = "WRONG APPLICATION URL \(Settings.shared.authorizationURL)"
        Log.error(msg)
        return reject(IVError(desc: msg))
      }
      
      Alamofire.request(authUrl, method: .post, parameters: ["client_id": applicationID
        ,"redirect_uri": "someUrl"
        ,"response_type": "code"
        ,"scope": "public" ]).responseJSON(completionHandler: { (response) in
          switch response.result {
            case .success(let val):
              Log.info("GOT A VALUE: \(val)")
//            let token = UnsplashAccessToken(appId: self.appId, accessToken: value["access_token"]! as! String)
//            Keychain.set(self.appId, value: token.accessToken)
//            completion(token, nil)
            case .failure(let err):
              Log.error("GOT AN ERROR: \(err.localizedDescription)")
//            let error = self.extractErrorFromData(response.data!)
//            completion(nil, error)
          }
        })
      
      
//      self.checkIfReachable().then(execute: { (Void) -> Void in
//        self.patientSDK.patientCancelConsultation(consultationId: consultationId, success: {
//          fulfill(Swift.Void())
//        }) { error in
//          reject(PatientErrorUI(error: error))
//        }
//      }).catch(execute: { (error) in
//        reject(error)
//      })
    }
    

    
//    Alamofire.request(.POST, Settings.shared.authorizationURL, parameters: ["client_id": applicationID
//      ,"redirect_uri": "someUrl"
//      ,"response_type": "code"
//      ,"scope": "public" ], encoding: .JSON)
//      .response { request, response, data, error in
//        let dataString = NSString(data: data!, encoding:NSUTF8StringEncoding)
//        println(dataString)
//    }
  }
}
