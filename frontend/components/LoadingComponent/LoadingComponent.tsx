import styles from '../../styles/LoadingComponent.module.scss';
import Image from 'next/image';
import logo from '../../images/logo.png';

const LoadingComponent = () => {

    return (
        <>
            <div id={styles.LoadingComponent}>
                <div id={styles.logoImgBox}>
                    <Image src={logo} alt="logo" />
                    <div id={styles.loadingText}>
                    Loading!
                    </div>
                </div>
                
            </div>
        </>
    );
}

export default LoadingComponent;