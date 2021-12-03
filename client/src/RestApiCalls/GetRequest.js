import {serverUrl} from "../Utils/Constant";

export const get = async (path) => {
    console.log(serverUrl);
    return await fetch(serverUrl + path, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        // mode: 'no-cors', //not good return wrong response
    });
}