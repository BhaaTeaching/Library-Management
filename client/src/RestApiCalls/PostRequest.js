import {buildBookRequestDto} from "../Components/AddBook/BuildDto";
import {serverUrl} from "../Utils/Constant";
import axios from "axios";

export const post = async (path, values) => {
     return await fetch(serverUrl + path, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(values)
    });
}

export const uploadTableOfContent = async (path, tableOfContent) => {
    debugger;
    const formData = new FormData();
    formData.append('tableOfContent', tableOfContent)
    return axios.post(serverUrl + path, formData,{});
        return await fetch(serverUrl + path, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'multipart/form-data',
        },
        body: formData
    });
}