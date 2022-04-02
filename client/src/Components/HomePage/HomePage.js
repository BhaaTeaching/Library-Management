import React from 'react';
import styled from "styled-components";
import LibraryImage from '../../Images/library-facebook-cover.jpeg'
import {HorizintalBox} from "../../Utils/Common";

const HomePage = () => {

    return (
        <div>
            <TitleWrapper>
               <h2>
                   Manage your library
               </h2>
            </TitleWrapper>
            {/*<img src={LibraryImage}/>*/}

            <HorizintalBox>
                <ImageBackground>
                    <img src={LibraryImage} alt={'Library'}/>
                </ImageBackground>
            </HorizintalBox>

        </div>
    )
};

export default HomePage;
const TitleWrapper = styled.div`
    color: #58430A;
    font-weight: bold;
    text-align-last: center;

`;

const ImageBackground = styled.div`
img{
    width: 112rem;
    }
`;
