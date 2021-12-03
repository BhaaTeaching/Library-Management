import {useEffect, useState} from "react";
import PropTypes from 'prop-types';
import './GetAllBooks.css'
import BootstrapTable from 'react-bootstrap-table-next';
import styled from "styled-components";
import {get} from "../../RestApiCalls/GetRequest";
import paginationFactory from 'react-bootstrap-table2-paginator';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.css';
import 'react-bootstrap-table2-filter/dist/react-bootstrap-table2-filter.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.css';
import VerifiedIcon from "@mui/icons-material/Verified";
import {Button, colors, IconButton, Snackbar} from "@material-ui/core";
import Dateformat from 'dateformat';
import CloseIcon from '@mui/icons-material/Close';
import {useNavigate} from "react-router-dom";
// import ToolkitProvider, { Search } from 'react-bootstrap-table2-toolkit';

const GetAllBooks = ({editCallback}) => {
    const [books, setBooks] = useState("");
    const [isBlob, setIsBlob] = useState(true);
    const [noFileExist, setNoFileExist] = useState('');
    let navigate = useNavigate();

    useEffect(() => {
        debugger
        console.log("editCallback", editCallback);
        get("/get-books").then(response => {
            debugger;
            if (response.ok) {
                debugger;
                response.json().then(json => {
                    setBooks(JSON.parse(JSON.stringify(json)));
                });
            }
        });
    }, []);

    const bookNameFormatter = (cell, row) => {
        return (
            <div>
                <Button variant="text" onClick={() => editCallback(row.id)}>{`${row.name} (${row.id})`}</Button>
            </div>
        );
    };

    const dateFormatter = (cell) => {
            return (
                <>
                    {cell ?
                        <span>{`${Dateformat(cell, 'dd mmm yyyy')}`}</span> : <span>N/A</span>
                    }
                </>
            );
        }
    ;

    const availableFormatter = cell => {
        return (
            <>
                {cell === true ?
                    <AvailableLoan/> : <NotAvailableLoan/>
                }
            </>
        );
    };

    const downloadTableOfContentFormatter = (cell, row) => {
        const downloadTableOfContent = (s) => {
            console.log("s", s)
            get("/download-table-of-content/" + s).then(response => {
                if (response.status === 405) {
                    setIsBlob(false);
                    response.clone().json().then(json => {
                        setNoFileExist(json.message);
                    });
                } else {
                    setIsBlob(true);
                    response.blob().then(blob => {
                        let url = window.URL.createObjectURL(blob);
                        let a = document.createElement('a');
                        a.href = url;
                        a.download = "filename.pdf";
                        document.body.appendChild(a); // we need to append the element to the dom -> otherwise it will not work in firefox
                        a.click();
                        a.remove();  //afterwards we remove the element again
                    });
                }
            })
        }

        return (
            <>
                <Button onClick={() => downloadTableOfContent(row.id)} variant="text">Download Table Of Content</Button>
            </>
        );
    };

    const columns = [{
        dataField: 'id',
        hidden: true,
    }, {
        dataField: 'name',
        text: 'Book Name',
        formatter: bookNameFormatter
    }, {
        dataField: 'subject',
        text: 'Subject'
    }, {
        dataField: 'author',
        text: 'Author'
    }, {
        dataField: 'existingCopies',
        text: 'Existing Copies'
    }, {
        dataField: 'nearestDateToReturn',
        text: 'Nearest Date To Return',
        formatter: dateFormatter,
    }, {
        dataField: 'isAvailable',
        text: 'Loan Availability',
        formatter: availableFormatter,
    },
        {
            dataField: 'id',
            text: 'Download Table Of Content',
            formatter: downloadTableOfContentFormatter,
        }
    ];

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        setIsBlob(true);
    };

    const action = (
        <>
            <IconButton
                size="small"
                aria-label="close"
                color="inherit"
                onClick={handleClose}
            >
                <CloseIcon fontSize="small"/>
            </IconButton>
        </>
    );

    return (
        <div>
            <h1>{"get all books"}</h1>
            {!isBlob && <Snackbar
                open={!isBlob}
                autoHideDuration={6000}
                onClose={handleClose}
                message={noFileExist}
                action={action}
            />}
            <AddBookButton>
                <Button onClick={() => navigate("/addBook")} variant="outlined">Add</Button>
            </AddBookButton>
            {/*<ToolkitProvider*/}
            {/*    keyField="id"*/}
            {/*    data={ books }*/}
            {/*    columns={ columns }*/}
            {/*    search*/}
            {/*>*/}
            {/*    <Search/>*/}
            {/*</ToolkitProvider>*/}
            <TableContainerStyle bootstrap4 keyField='id' data={books} columns={columns}
                                 pagination={paginationFactory()}/>
        </div>
    )
};

GetAllBooks.prototype = {
    editCallback: PropTypes
}

export default GetAllBooks;

const AvailableLoan = styled(VerifiedIcon)`
color: ${theme => colors.green[700]};
`;

const NotAvailableLoan = styled(VerifiedIcon)`
color: ${theme => colors.red[700]};
`;

const TableContainerStyle = styled(BootstrapTable)`
     max-height: 100%;
`;

const AddBookButton = styled.div`
    float:right;
    margin: 0 0.5rem 0.5rem 0;

`;