import { useEffect, useState } from "react";
import axios from 'axios';
import './Home.css';

export default function Home(){
  
    // 상태정의 state필요
    // -carlist는 백앤드 spring에서 받아온 차량의 목록
    //  데이터를 저장하는 변수이다.
    // -setCarList는 데이터를 받아 온 후, 화면을 다시 re-Rendering 위해 업데이트 함
    // -초기값은 빈 배열 []로 설정하여 데이터가 들어 오기전의 에러가 나지 않도록 한다. 
    const [carlist, setCarList]=useState([])
    
    // axios 사용방법
    useEffect(()=>{
        // vite Proxy 활용 /api/cars로 요청 보낸다
        // 실제는 vite.config.js의 설정에 의해서
        //  'http://localhost:8090/api/cars'로 전달된다.
        axios.get('/api/cars')
        .then((res) =>{
            // res.data에는 백앤드(SpringBoot)에서 JSON 형태로 보낸
            // List<CarProudctDTO> 데이터가 담겨 있다.
            console.log("받아온 데이터 :",res.data);
            // 받아온 데이터를 setCarList상태에 저장 -> 다시 랜더링된다.
            setCarList(res.data);
        })
        .catch((error)=>{
            // 서버가 꺼져있거나, 주소 틀린 경우 실행된다.
            console.error("데이터 로딩 에러:",error);
        })
    },[])
    
    return(
        <section>
            <div id="section_wrap">
                <div className="word">HOME</div>
                <div className="content">
                    <div className="carList">
                        {carlist.length > 0 ? (
                            // map으로 반복하여 목록 출력
                            carlist.map((car)=>(
                               <>
                                  <div className="carItem" key={car.no}>
                                    <img src={`/img/car/${car.img}`} alt={car.carName} />
                                    <div className="carName">{car.carName}</div>
                                    <div className="carPrice">{Number(car.price).toLocaleString()}</div>
                                  </div>
                                
                               </> 
                                
                            ))
                        ):(  <p>등록된 차량이 없습니다.</p>)}


                    </div>
                </div>
            </div>
        </section>
    )
}