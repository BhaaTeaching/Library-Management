import styled from "styled-components";
import React from "react";
import {TextField} from "@mui/material";

export const TextFieldReference = React.forwardRef((props, ref) => (
    <TextField ref={ref} {...props} />
));

export const TextFieldStyle = styled.div`
    margin-top: 1.5rem;
    text-align: center;
`;

export const HorizintalBox = styled.div`
    display: flex;
`;

export const SubmitButton = styled.div`
    margin-top: 0.5rem;
`;