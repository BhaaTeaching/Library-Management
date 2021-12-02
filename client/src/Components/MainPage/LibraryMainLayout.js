import React, {useCallback} from 'react';
import GetAllBooks from "../GetAllBooks/GetAllBooks";
import { Routes, Route, useNavigate, BrowserRouter as Router } from "react-router-dom";
import AddBook from "../AddBook/AddBook";

const LibraryMainLayout = () => {

    let navigate = useNavigate();
    const addEditCallback = useCallback(() => {
        console.log(" to ");
     navigate("/addEditBook");
    }, [])



    return (
        <div>
            <Routes>
                <Route path="/" element={<GetAllBooks editCallback={addEditCallback}/>} />
                <Route path="/addEditBook" element={<AddBook />} />
            </Routes>
        </div>
    );

};

export default LibraryMainLayout;

