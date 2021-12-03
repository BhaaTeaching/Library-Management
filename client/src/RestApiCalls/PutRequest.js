import {serverUrl} from "../Utils/Constant";
import {buildBookRequestDto} from "../Components/AddBook/BuildDto";

export const put = async (path, values) => {

    await fetch(serverUrl + path, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(buildBookRequestDto(values))
    });
}