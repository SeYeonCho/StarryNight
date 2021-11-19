import React from "react";
import styles from "../../styles/MainPage.module.scss";
import MainBody from "./MainBody";
import MainHeader from "./MainHeader";

const MainPage = () => {
    return (
        <>
            <div id={styles.mainBox}>
                <MainHeader />
                <MainBody />
            </div>
        </>
    );
}

export default MainPage;