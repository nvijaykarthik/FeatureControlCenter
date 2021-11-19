console.log("Running post build js")

const fs = require("fs")
const fse = require("fs-extra")

let srcFolder="build"
let destFolder="../fccserver/src/main/resources/static"

fse.copy(srcFolder,destFolder, err => {
    if (err) return console.error(err)
    console.log('Files copied successfully')
  });
