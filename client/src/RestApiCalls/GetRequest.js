import {serverUrl} from "../Utils/Constant";

export const get = async (path) => {
    return await fetch(serverUrl + path, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
}