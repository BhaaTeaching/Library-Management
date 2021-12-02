import {useEffect, useState} from "react";
import {get} from "../../RestApiCalls/GetRequest";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Dateformat from "dateformat";
import VerifiedIcon from '@mui/icons-material/Verified';
import styled from "styled-components";
import {Button, colors} from "@mui/material";
import Snackbar from '@mui/material/Snackbar';
import CloseIcon from '@mui/icons-material/Close';
import IconButton from '@mui/material/IconButton';
import PropTypes from 'prop-types';
import './GetAllBooks.css'

const GetAllBooks = ({editCallback}) => {
    const [books, setBooks] = useState();
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [isBlob, setIsBlob] = useState(true);
    const [noFileExist, setNoFileExist] = useState('');
    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    useEffect(() => {
        debugger
        console.log("editCallback", editCallback);
        get("/get-books").then(response => {
            if (response.ok) {
                debugger;
                response.json().then(json => {
                    setBooks(json);
                });
            }
        });
    }, []);

    const bookIdFormatter = ({cell, row}) => {
        return (
            <>
                <HiddenId>{cell}</HiddenId>
            </>
        );
    };

    const bookNameFormatter = (cell, row) => {
        return (
            <div>
                {console.log("cell ", cell)}
                {console.log("row ", row)}
            <Button onClick={editCallback}>{cell}</Button>
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

    const downloadTableOfContentFormatter = cell => {
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
                <Button onClick={() => downloadTableOfContent(cell)} variant="text">Download Table Of Content</Button>
            </>
        );
    };

    const columns = [
        {
            id: 'name',
            label: 'Name',
            minWidth: 170,
            format: bookNameFormatter,
        },
        {
            id: 'subject',
            label: 'Subject',
            minWidth: 170,
            align: 'center',
        },
        {
            id: 'author',
            label: 'Author',
            minWidth: 100,
            align: 'center',
        },
        {
            id: 'existingCopies',
            label: 'Existing Copies',
            minWidth: 170,
            align: 'center',
        },
        {
            id: 'nearestDateToReturn',
            label: 'Nearest Date To Return',
            minWidth: 170,
            align: 'center',
            format: dateFormatter,
        },
        {
            id: 'isAvailable',
            label: 'Loan Availability',
            minWidth: 170,
            align: 'center',
            format: availableFormatter,
        },
        {
            id: 'id',
            label: 'Download Table Of Content',
            minWidth: 170,
            align: 'left',
            format: downloadTableOfContentFormatter,
        }
    ]
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
            <TableContainerStyle sx={{maxHeight: 440}}>
                <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                        <TableRow>
                            {columns.map((column) => (
                                <TableCell
                                    key={column.id}
                                    align={column.align}
                                    style={{minWidth: column.minWidth}}
                                >
                                    {column.label}
                                </TableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {books?.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((row) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                                        {columns.map((column) => {
                                            const value = row[column.id];
                                            return (
                                                <TableCell key={column.id} align={column.align}>
                                                    {column.format
                                                        ? column.format(value)
                                                        : value}
                                                </TableCell>
                                            );
                                        })}
                                    </TableRow>
                                );
                            })}
                    </TableBody>
                </Table>
            </TableContainerStyle>
            <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={books?.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
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

const HiddenId = styled.div`
    display: none;
`;

const TableContainerStyle = styled.div`
     max-height: 100%;
`;