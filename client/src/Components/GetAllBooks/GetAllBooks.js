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
import {post} from "../../RestApiCalls/PostRequest";
import {NotificationContainer, NotificationManager} from 'react-notifications';
// import ToolkitProvider, { Search } from 'react-bootstrap-table2-toolkit';
import 'react-notifications/lib/notifications.css';
import {OverflowMenuItem} from "carbon-components-react";
import OverflowMenu from "carbon-components-react/lib/components/OverflowMenu/next/OverflowMenu";
import {remove} from "../../RestApiCalls/DeleteRequest";
import {useDispatch, useSelector} from "react-redux";
import {setBooksStore} from "../../Redux/baseActions";

const GetAllBooks = ({editCallback}) => {
    const [books, setBooks] = useState("");
    const [isBlob, setIsBlob] = useState(true);
    const [noFileExist, setNoFileExist] = useState('');
    let navigate = useNavigate();
    const booksStore = useSelector(state => state.books);
    const userStore = useSelector(state => state.user);
    const dispatch = useDispatch();

    useEffect(() => {
        get("/get-books").then(response => {
            if (response.ok) {
                response.json().then(json => {
                    const booksData = JSON.parse(JSON.stringify(json));
                    dispatch(setBooksStore(booksData))
                    setBooks(booksData);
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

    const loanBookFormatter = (cell, row) => {
        const loanBook = () => {
          //  post(`/loan-book?bookId=${row.id}&userId=206052933`).then(response => {
            debugger;
            post(`/loan-book?bookId=${row.id}&userId=${userStore.id}`).then(response => {
                if (response.ok) {
                    get("/get-books").then(response => {
                        if (response.ok) {
                            response.json().then(json => {
                                setBooks(JSON.parse(JSON.stringify(json)));
                            });
                        }
                    });
                    response.json().then(json => {
                        NotificationManager.success(`Book: ${json.book.name} was ordered for user: ${json.user.name}`, 'Loan Succeed');
                    });
                } else {
                    response.clone().json().then(json => {
                        NotificationManager.error(json.message, 'Loan Failed');
                    });
                }
            })
        }

        const orderBook = () => {
            post(`/order-book?bookId=${row.id}&userId=206052933`).then(response => {
                if (response.ok) {
                    response.json().then(json => {
                        NotificationManager.success(`Book: ${json.book.name} was ordered for user: ${json.user.name}`, 'Order Succeed');

                    });
                } else {
                    response.clone().json().then(json => {
                        NotificationManager.error(json.message, 'Order Failed');
                    });
                }
            });
        }

        return (
            <div>
                {row?.isAvailable ?
                    <Button onClick={loanBook}>Loan</Button> : <Button onClick={orderBook}>Order</Button>}
            </div>
        );
    };

    const moreFormatter = (cell, row) => {
        const Edit = () => {
            return (
                <OptionText>{"Edit"}</OptionText>
            );
        };

        const Delete = () => {
            return (
                <OptionText>{"Remove"}</OptionText>
            );
        };
        const deleteCallback = (bookId) => {
            remove(`/remove-book/${bookId}`).then(response => {
                if (response.ok) {
                    let deepCopy = JSON.parse(JSON.stringify(books));
                    deepCopy = deepCopy.filter(book => book?.id !== bookId)
                    dispatch(setBooksStore(deepCopy))
                    setBooks(deepCopy)
                    response.json().then(json => {
                        NotificationManager.success(`Book: ${json?.name} was deleted successfully`, 'Delete Succeed');
                    });
                } else {
                    response.clone().json().then(json => NotificationManager.error(json.message, 'Delete Failed'));
                }
            });
            navigate('/')
        }

        return (
            <OverflowMenu>
                <OverflowMenuItem onClick={() => editCallback(row.id)} itemText={<Edit/>}/>
                <OverflowMenuItem onClick={() => deleteCallback(row.id)} itemText={<Delete/>}/>
            </OverflowMenu>
        );
    }

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
        },
        {
            dataField: 'loanBook',
            text: 'Loan Book',
            formatter: loanBookFormatter,
        },
        {
            dataField: 'more',
            formatter: moreFormatter,
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
            <NotificationContainer/>
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

const OptionText = styled.span`
  margin-inline-start: 0.3125rem;
`;