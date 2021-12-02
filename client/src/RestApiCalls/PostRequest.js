import {buildBookRequestDto} from "../Components/AddBook/BuildDto";
import {serverUrl} from "../Utils/Constant";

export const post = async (path, values) => {
    await fetch(serverUrl + path, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(buildBookRequestDto(values))
    });
}