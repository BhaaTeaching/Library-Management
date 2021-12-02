import {serverUrl} from "../Utils/Constant";

export const get = async (path) => {
    console.log(serverUrl);
    debugger;
    return await fetch(serverUrl + path, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
}