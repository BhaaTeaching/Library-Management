import React, {useCallback} from 'react';
import GetAllBooks from "../GetAllBooks/GetAllBooks";
import {Route, Routes, useNavigate} from "react-router-dom";
import AddEditBook from "../AddBook/AddEditBook";
import {Container, Nav, Navbar} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import UserInfo from "../UserInfo/UserInfo";
import {remove} from "../../RestApiCalls/DeleteRequest";
import {NotificationManager} from "react-notifications";
import {get} from "../../RestApiCalls/GetRequest";

const LibraryMainLayout = () => {

    let navigate = useNavigate();
    const addEditCallback = useCallback((bookId) => {
        console.log(" to ");
        navigate(`/editBook/${bookId}`);
    }, [])



    return (
        <div>
            {/*<NavbarTest/>*/}
            <Navbar bg="light" expand="lg">
                <Container>
                    <Navbar.Brand href="/">Library</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="/">Home</Nav.Link>
                        <Nav.Link href="/userInfo">User Info</Nav.Link>
                    </Nav>

                </Container>
            </Navbar>
            <Routes>
                <Route exact path="/" element={<GetAllBooks editCallback={addEditCallback}/>}/>
                <Route path="/addBook" element={<AddEditBook/>}/>
                <Route path="/editBook/:bookId" element={<AddEditBook/>}/>
                <Route path="/userInfo" element={<UserInfo userId={206052933}/>}/>
            </Routes>
        </div>
    );

};

export default LibraryMainLayout;

