import React, {useCallback} from 'react';
import GetAllBooks from "../GetAllBooks/GetAllBooks";
import {Route, Routes, useNavigate} from "react-router-dom";
import AddEditBook from "../AddBook/AddEditBook";

const LibraryMainLayout = () => {

    let navigate = useNavigate();
    const addEditCallback = useCallback((bookId) => {
        console.log(" to ");
        navigate(`/editBook/${bookId}`);
    }, [])


    return (
        <div>
            <Routes>
                <Route path="/" element={<GetAllBooks editCallback={addEditCallback}/>}/>
                <Route path="/addBook" element={<AddEditBook/>}/>
                <Route path="/editBook/:bookId" element={<AddEditBook/>}/>
            </Routes>
        </div>
    );

};

export default LibraryMainLayout;

