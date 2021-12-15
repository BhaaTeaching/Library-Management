import React, {useEffect, useRef, useState} from 'react';
import {Box, Paper, TextField} from "@mui/material";
import {get} from "../../RestApiCalls/GetRequest";
import styled from "styled-components";
import './UserInfo.css';

const UserInfo = ({userId}) => {
    const [user, setUser] = useState();
    const textRef = useRef();

    useEffect(() => {
        if (textRef.current)
            textRef.current.focus();
        get(`/get-user/${userId}`).then(response => {
            if (response.ok) {
                response.json().then(json => {
                    console.log(json);
                    setUser(json);
                });
            }
        });
    }, [])


    const TextFieldReference = React.forwardRef((props, ref) => (
        <TextField ref={ref} {...props} />
    ));

    return (
        <div>
            <Box
                sx={{
                    display: 'flex',
                    flexWrap: 'wrap',
                    '& > :not(style)': {
                        m: 2,
                        width: 1600,
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
                        {console.log("user?.countryCallingCode", user?.countryCallingCode)}
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
               
            {/* </Box> */}
        </div>
    );

};

export default UserInfo;


const TextFieldStyle = styled.div`
    margin-top: 1.5rem;
`;

const Wrapper = styled.div`
    width: -webkit-fill-available;
`;