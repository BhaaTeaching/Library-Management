import React, {useCallback} from 'react';
import GetAllBooks from "../GetAllBooks/GetAllBooks";
import {Route, Routes, useNavigate} from "react-router-dom";
import AddEditBook from "../AddBook/AddEditBook";
import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import UserInfo from "../UserInfo/UserInfo";
import Signup from "../Signup/Signup";
import Signin from "../Signin/Signin";
import HomePage from "../HomePage/HomePage";
import styled from "styled-components";
import {useSelector} from "react-redux";

const LibraryMainLayout = () => {

    let navigate = useNavigate();
    const authorizedUser = useSelector(state => state.user);
    const addEditCallback = useCallback((bookId) => {
        navigate(`/editBook/${bookId}`);
    }, [])



    return (
        <div>
            {/*<NavbarTest/>*/}
            <Navbar bg="light" expand="xxl">
                <Container>
                    {/*<Navbar.Brand href="/">Library</Navbar.Brand>*/}
                    <Nav className="me-auto">
                        <Nav.Link href="/">Home</Nav.Link>
                        <Nav.Link href="/bookOperations">Book Operations</Nav.Link>
                        <Nav.Link href="/userInfo">User Info</Nav.Link>
                        <AccordionStyle>
                            <NavDropdown title="Sign in" id="basic-nav-dropdown">
                                <NavDropdown.Item href="/Signin">Sign in</NavDropdown.Item>
                                <NavDropdown.Item href="/Signup">Sign up</NavDropdown.Item>
                            </NavDropdown>
                        </AccordionStyle>
                    </Nav>

                </Container>
            </Navbar>
            <Routes>
                <Route exact path="/" element={<HomePage/>}/>
                <Route exact path="/bookOperations" element={<GetAllBooks editCallback={addEditCallback}/>}/>
                <Route path="/Signup" element={<Signup/>}/>
                <Route path="/Signin" element={<Signin/>}/>
                <Route path="/addBook" element={<AddEditBook/>}/>
                <Route path="/editBook/:bookId" element={<AddEditBook/>}/>
                <Route path="/userInfo" element={<UserInfo userId={authorizedUser?.id}/>}/>
            </Routes>
        </div>
    );

};

export default LibraryMainLayout;

const AccordionStyle = styled.div`
  position: absolute;
  left: 90rem;
  width: 10rem;
`;