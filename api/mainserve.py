from fastapi import FastAPI, HTTPException

app = FastAPI()

with open('floordata.txt', 'r') as file:
    lines = file.readlines()
    for i, line in enumerate(lines):
        lines[i] = line.strip()

rooms = {}
valves = {}
corridors = {}
stairs = {}
washrooms = {}
elevators = {}

for line in lines:
    if line == "":
        pass
    elif line[0] == "#":
        pass
    else:
        temp = line.split()
        if temp[0][-1] in "B0123456789":
            rooms[temp[0]] = {'size': int(temp[1]), 'floor': int(temp[2]), 'restricted': temp[3], 'imgXpos': int(temp[4]), 'imgYpos': int(temp[5]), 'connected': temp[6:]}
        if temp[0][-1] == "V":
            valves[temp[0]] = {'size': int(temp[1]), 'floor': int(temp[2]), 'imgXpos': int(temp[3]), 'imgYpos': int(temp[4]), 'connected': temp[5:]}
        if temp[0][-1] == "K":
            corridors[temp[0]] = {'size': int(temp[1]), 'floor': int(temp[2]), 'imgXpos': int(temp[3]), 'imgYpos': int(temp[4]), 'connected': temp[5:]}
        if temp[0][-1] in "ST":
            stairs[temp[0]] = {'size': int(temp[1]), 'floorstart': int(temp[2]), 'floorend': int(temp[3]), 'f1x': int(temp[4]), 'f1y': int(temp[5]), 'f2x': int(temp[6]), 'f2y': int(temp[7]), 'connected': temp[8:]}
        if temp[0][-1] == "W":
            washrooms[temp[0]] = {'size': int(temp[1]), 'floor': int(temp[2]), 'gender': temp[3], 'imgXpos': int(temp[4]), 'imgYpos': int(temp[5]), 'connected': temp[6:]}
        if temp[0][-1] == "E":
            elevators[temp[0]] = {'size': int(temp[1]), 'floorstart': int(temp[2]), 'floorend': int(temp[3]), 'f1x': int(temp[4]), 'f1y': int(temp[5]), 'f2x': int(temp[6]), 'f2y': int(temp[7]), 'connected': temp[8:]}

dictionaries = {
    "r": rooms,
    "v": valves,
    "c": corridors,
    "s": stairs,
    "w": washrooms,
    "e": elevators,
}

@app.get("/get-dictionary/")
def get_dictionary(name: str):
    if name in dictionaries:
        return dictionaries[name]
    else:
        raise HTTPException(status_code=404, detail="Dictionary not found")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)
