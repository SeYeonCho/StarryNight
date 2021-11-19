import { useRouter } from 'next/router';
import Image from 'next/image';
import logo from '../../images/logo.png';
import styles from '../../styles/MainHeader.module.scss';

const MainHeader = () => {
    const router = useRouter();
    const moveHome = () => {

        router.push(`/`);
        
    };
    
    const moveKeyword = () => {
        router.push('/search');
    }

    return (
        <div id={styles.headerBox}>
            <div id={styles.logoImgBox} onClick={moveHome}>
                <Image src={logo} alt="logo"/>
            </div>
        </div>
    );
}

export default MainHeader;