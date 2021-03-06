import React, {useEffect, useRef, useState} from 'react';
import {Box, Paper, TextField} from "@mui/material";
import {get} from "../../RestApiCalls/GetRequest";
import styled from "styled-components";
import './UserInfo.css';
import {TextFieldReference, TextFieldStyle} from "../../Utils/Common";

const UserInfo = ({userId}) => {
    const [user, setUser] = useState(null);
    const textRef = useRef();

    useEffect(() => {
        if (textRef.current)
            textRef.current.focus();
        get(`/get-user/${userId}`).then(response => {
            if (response.ok) {
                response.json().then(json => {
                    setUser(json);
                });
            }
        });
    }, [])




    return (
        <div>
            <Box
                sx={{
                    display: 'flex',
                    flexWrap: 'wrap',
                    '& > :not(style)': {
                        m: 2,
                        width: 2112,
                        height: 500,
                    },
                }}
                children={
                    <Paper elevation={3}>
                        <TextFieldStyle>
                            <TextFieldReference
                                id="outlined-read-only-input"
                                label={"User Id"}
                                value={user?.id}
                                InputProps={{
                                    readOnly: true,
                                }}
                                inputRef={textRef}
                            />
                        </TextFieldStyle>
                        <TextFieldStyle>
                            <TextFieldReference
                                id="outlined-read-only-input"
                                label={"User Name"}
                                value={user?.name}
                                InputProps={{
                                    readOnly: true,
                                }}
                                inputRef={textRef}
                            />
                        </TextFieldStyle>
                        <TextFieldStyle>
                            <TextFieldReference
                                id="outlined-read-only-input"
                                label={"User Phone"}
                                value={user?.countryCallingCode + '-' + user?.phone}
                                InputProps={{
                                    readOnly: true,
                                }}
                                inputRef={textRef}
                            />
                        </TextFieldStyle>
                        <TextFieldStyle>
                            <TextFieldReference
                                id="outlined-read-only-input"
                                label={"User Email"}
                                value={user?.email}
                                InputProps={{
                                    readOnly: true,
                                }}
                                inputRef={textRef}
                            />
                        </TextFieldStyle>
                    </Paper>
                }
            />
        </div>
    );

};

export default UserInfo;


