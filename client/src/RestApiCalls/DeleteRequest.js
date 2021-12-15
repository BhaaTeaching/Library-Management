import {serverUrl} from "../Utils/Constant";

export const remove = async (path) => {
    return await fetch(serverUrl + path, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });
}