from typing import List
import firebase_admin
from firebase_admin import db

cred_object = firebase_admin.credentials.Certificate("subway-chaos-firebase-adminsdk-zvzzp-5aec150286.json")
default_app = firebase_admin.initialize_app(cred_object, {
	"databaseURL": "https://subway-chaos-default-rtdb.firebaseio.com/" 
	})

print(db.reference("/level1/uid/").get())