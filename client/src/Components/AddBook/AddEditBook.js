import React, {useEffect, useState} from 'react';
import {Button, colors, Paper} from "@mui/material";
import {TextField} from 'final-form-material-ui';
import './AddBook.css'
import {Field, Form} from 'react-final-form';
import styled from "styled-components";
import 'cors';
import {post, uploadTableOfContent} from "../../RestApiCalls/PostRequest";
import {get} from "../../RestApiCalls/GetRequest";
import VerifiedIcon from '@mui/icons-material/Verified';
import {useNavigate, useParams} from "react-router-dom";
import {put} from "../../RestApiCalls/PutRequest";
import {FileUploaderDropContainer} from "carbon-components-react";
import 'carbon-components/css/carbon-components.min.css';
import {buildBookRequestDto} from "./BuildDto";

const AddEditBook = () => {
    const [book, setBook] = useState();
    const [isEdit, setIsEdit] = useState();
    const [tableOfContentFile, setTableOfContentFile] = useState();

    const {bookId} = useParams();
    let navigate = useNavigate();

    const onSubmit = async (values) => {
        const response = isEdit ? await put(`/edit-book/${bookId}`, values) : await post('/add-book', buildBookRequestDto(values));
        if (response?.ok) {
            response.json().then(json => {
                setBook(json);
            });
        }
        if (tableOfContentFile != null || tableOfContentFile !== undefined) {
            isEdit ? await uploadTableOfContent(`/edit-table-of-content/${bookId}`, tableOfContentFile) : await uploadTableOfContent(`/upload-table-of-content/${book?.id}`, tableOfContentFile);
        }
        navigate("/")
    }

    const validate = (values) => {
        console.log("validate");
        //TODO: need to implement it
    }

    useEffect(() => {
        const isEditTest = window.location.pathname.includes("editBook");
        setIsEdit(isEditTest);
        get(`/get-book/${bookId}`).then(response => {
            if (response.ok) {
                response.json().then(json => {
                    setBook(json);
                });
            }
        });
    }, []);

    const handleFileUpload = (file) => {
        setTableOfContentFile(file.target.files[0]);
    }

    return (
        <div>
            <CanLoan/>
            <Form
                onSubmit={onSubmit}
                initialValues={{}}
                validate={validate}
                render={({handleSubmit, reset, submitting, pristine, values}) => (
                    <form onSubmit={handleSubmit}>
                        <Paper>
                            <Field id={"bookName"} label={"Name"} variant={"outlined"} component={TextField}
                                   name={"bookName"} defaultValue={book?.name}/>
                            <FieldMargin/>
                            <Field id={"author"} label={"Author"} variant={"outlined"} component={TextField}
                                   name={"authorName"} defaultValue={book?.author}/>
                            <FieldMargin/>
                            <Field id={"subject"} label={"Subject"} variant={"outlined"} component={TextField}
                                   name={"bookSubject"} defaultValue={book?.subject}/>
                            <FieldMargin/>
                            <Field id={"numberOfCopies"} label={"Copies"} variant={"outlined"}
                                   component={TextField} name={"numberOfCopies"} type={"number"}
                                   defaultValue={book?.copies}/>
                            <FieldMargin/>
                            <Field id={"bookLocation"} label={"Location"} variant={"outlined"}
                                   component={TextField} name={"bookLocation"} defaultValue={book?.location}/>
                            <FileUploaderDropContainer id={'tableOfContentUploader'} accept={['.pdf']}
                                                       labelText={'Upload Table Of Content'}
                                                       onAddFiles={handleFileUpload}/>
                            <Button variant="contained" type={"submit"}>Save</Button>

                        </Paper>
                    </form>
                )}
            />
        </div>
    );

};

export default AddEditBook;

const FieldMargin = styled.div`
margin-top:1rem
`;

const CanLoan = styled(VerifiedIcon)`
color: ${theme => colors.green[700]}
`;
