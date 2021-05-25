from typing import List
import firebase_admin
from firebase_admin import db

cred_object = firebase_admin.credentials.Certificate("subway-chaos-firebase-adminsdk-zvzzp-5aec150286.json")
default_app = firebase_admin.initialize_app(cred_object, {
	"databaseURL": "https://subway-chaos-default-rtdb.firebaseio.com/" 
	})

def ListDir(Dir) -> str:
    ref = None
    try:
        ref = db.reference(Dir).get()
    except: pass
    return ref

def ProcessCommand(ProcessString, CurrentDir) -> str:
    if "cd" in ProcessString.lower():
        ProcessString = ProcessString.replace("cd", "").strip()

        if ProcessString == "../":
            IndexOf = 0
            for IndexOf in range(len(ProcessString)):
                if ProcessString[IndexOf] == "/":
                    break
            return CurrentDir[:IndexOf - 1] if IndexOf - 1 != 0 else "/"

        OriginalPath = CurrentDir

        if (CurrentDir[-1] != "/"):
            CurrentDir += "/"

        CurrentDir += ProcessString
        if ListDir(CurrentDir) != None:
            return CurrentDir 
        else:
            print("NULL PATH")
            return OriginalPath

    elif "save" in ProcessString.lower():
        ProcessString = ProcessString.replace("save", "").strip()

        with open(CurrentDir.replace("/", "") + ".json", "w+") as f:
            f.write(str(ListDir(CurrentDir)))
        
        print("Cool it worked")

        return CurrentDir

    elif "inject" in ProcessString.lower():
        ProcessString = ProcessString.replace("inject", "").strip()

    #elif ("set" in ProcessString.lower()):
    #    ProcessString = ProcessString.replace("set", "").strip()

    return "/"

Dir = "/"

if __name__ == "__main__":
    print(ListDir("/"))
    while (InputStuff := input(">>>")).lower() != "exit":
        Dir = ProcessCommand(InputStuff, Dir)
        print("\n [" + Dir + "]\n")
        print(ListDir(Dir))
    