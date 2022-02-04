import React, {useState} from 'react';
import {Formik} from 'formik';
import PhoneCode from 'react-phone-code';
import {post} from "../../RestApiCalls/PostRequest";
import {NotificationContainer, NotificationManager} from "react-notifications";
import {HorizintalBox, SubmitButton, TextFieldReference, TextFieldStyle} from "../../Utils/Common";
import styled from "styled-components";
import {Button} from "@material-ui/core";

const Signup = () => {
    const [countryCallingCode, setCountryCallingCode] = useState('+972')
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
        values.countryCallingCode = countryCallingCode;
        post('/add-user', values).then(response => {
            debugger;
            if (response.ok) {
                response.json().then(json => {
                    NotificationManager.success(``, 'Registration Succeed'); //not working

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
                            <TextFieldReference
                                type={"number"}
                                label={"id"}
                                name={"id"}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                value={values.id}
                                error={errors.id !== '' && errors.id !== undefined}
                                helperText={errors.id !== '' || errors.id !== undefined ? errors.id : ''}
                            />
                            <TextFieldStyle>
                                <TextFieldReference
                                    type={"text"}
                                    label={"Full Name"}
                                    name={"name"}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.name}
                                    error={errors.name !== '' && errors.name !== undefined}
                                    helperText={errors.name !== '' || errors.name !== undefined ? errors.name : ''}
                                />
                            </TextFieldStyle>
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
                            <PhoneStyle>
                                <TextFieldStyle>
                                    <HorizintalBox>
                                        <PhoneCountryCallingCodeStyle
                                            onSelect={code => setCountryCallingCode(code)}
                                            showFirst={['IL']}
                                            defaultValue='select county'
                                            id='phoneCodeId'
                                            name='phoneCodeName'
                                            className='phoneCodeClassName'
                                        />
                                        <TextFieldReference
                                            type={"number"}
                                            label={"Phone"}
                                            name={"phone"}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.phone}
                                            error={errors.phone !== '' && errors.phone !== undefined}
                                            helperText={errors.phone !== '' || errors.phone !== undefined ? errors.phone : ''}
                                        />
                                    </HorizintalBox>
                                </TextFieldStyle>
                            </PhoneStyle>
                            <SubmitButton>
                                <Button variant="contained" type="submit" disabled={isSubmitting}>
                                    Submit
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

export default Signup;

const PhoneCountryCallingCodeStyle = styled(PhoneCode)`
   padding: 1rem;
   margin-right: 0.5rem;
`;

const PhoneStyle = styled.div`
    width: 90rem;
    margin: 0 auto;
`;