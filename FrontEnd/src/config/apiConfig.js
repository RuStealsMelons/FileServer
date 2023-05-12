const identity = {
    owner: "KeepHeartbeat",
    repo: "file",
    token: "e92fb28fd96cf10ea12f727d5a861a9f",
    branch: "master"
}

const textMapping = {
    "txt": "text",
    "csv": "text",
    "json": "text",

}

const officeMapping = {
    "doc": "office",
    "docx": "office",
    "pdf": "office",
    "xsls": "office"
}

const imageMapping = {
    "jpg": "image",
    "jpeg": "image",
    "gif": "image",
    "png": "image",
    "bmp": "image"
}

const audioMapping = {
    "mp3": "audio",
    "wav": "audio",
    "wma": "audio",
    "aac": "audio",
}

const videoMapping = {
    "mp4": "video",
    "avi": "video",
    "flv": "video",
    "wmv": "video",
}

const databaseMapping = {
    "sql": "database",
    "mdb": "database"
}

const executableMapping = {
    "exe": "executable",
    "dll": "executable",
    "jar": "executable"
}

const mapping = {
    ...textMapping,
    ...officeMapping,
    ...imageMapping,
    ...audioMapping,
    ...videoMapping,
    ...databaseMapping,
    ...executableMapping,

}

export default {
    identity,
    mapping
}
