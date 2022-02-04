import React, {useState} from 'react';
import {Formik} from 'formik';
import {NotificationContainer, NotificationManager} from "react-notifications";
import {SubmitButton, TextFieldReference, TextFieldStyle} from "../../Utils/Common";
import {Button} from "@material-ui/core";
import {get} from "../../RestApiCalls/GetRequest";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {setUserStore} from "../../Redux/baseActions";

const Signin = () => {
    let navigate = useNavigate();
    let dispatch = useDispatch();

    const validate = values => {
        const errors = {};
        if (!values.email) {
            errors.email = 'Required';
        } else if (
            !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
        ) {
            errors.email = 'Invalid email address';
        }
        return errors;
    }

    const onSubmit = (values, {setSubmitting}) => {
        get('/sigin-user/' + values.email + '/' + values.password).then(response => {
            debugger;
            if (response.ok) {
                response.json().then(json => {
                    console.log("sign in", json);
                    NotificationManager.success(`Sign in`, 'Registration Succeed'); //not working
                    dispatch(setUserStore(json));
                    navigate("/");
                });
            } else {
                response.clone().json().then(json => {
                    NotificationManager.error(json.message, 'Registration Failed');
                });
            }
        });
    }

    return (
        <div>
            <Formik
                initialValues={{email: '', password: ''}}
                validate={validate}
                onSubmit={onSubmit}
            >
                {({
                      values,
                      errors,
                      touched,
                      handleChange,
                      handleBlur,
                      handleSubmit,
                      isSubmitting,
                      /* and other goodies */
                  }) => (
                    <form onSubmit={handleSubmit}>
                        <TextFieldStyle>
                            <TextFieldStyle>
                                <TextFieldReference
                                    type={"text"}
                                    label={"E-mail"}
                                    name={"email"}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.email}
                                    error={errors.email !== '' && errors.email !== undefined}
                                    helperText={errors.email !== '' || errors.email !== undefined ? errors.email : ''}
                                />
                            </TextFieldStyle>
                            <TextFieldStyle>
                                <TextFieldReference
                                    type={"password"}
                                    label={"Password"}
                                    name={"password"}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.password}
                                    error={errors.password !== '' && errors.password !== undefined}
                                    helperText={errors.password !== '' || errors.password !== undefined ? errors.password : ''}
                                />
                            </TextFieldStyle>
                            <SubmitButton>
                                <Button variant="contained" type="submit" disabled={isSubmitting}>
                                    Sign in
                                </Button>
                            </SubmitButton>
                        </TextFieldStyle>

                    </form>
                )}
            </Formik>
            <NotificationContainer/>
        </div>
    )
};

export default Signin;

