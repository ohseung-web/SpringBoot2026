import { useState, useContext } from 'react';
import { AuthContext } from '../contexts/AuthContext';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Member.css';

export default function Login() {
 
    const [id,setId]=useState('');
    const [pw,setPw]=useState('');
    const navigate = useNavigate();
    const {login} = useContext(AuthContext);

    // 로그인 버튼 클릭시 실행 핸들러
    const loginHandler = () =>{
        //id,pw  유효성 검사
        if(id === ''){
            alert("아이디 입력하세요");
            return;
        }
        if(pw === ''){
            alert('비밀번호를 입력하세요');
            return;
        }

        // 서버로 로그인 요청
        axios.post('/api/member/login',{id:id,pw:pw})
        .then((res) => {
            // 로그인 성공과 실패 예외처리
            if(res.data){
                //로그인 성공 => 홈(/)
                alert(`${res.data.id}님 환영합니다.`);
                // AuthContext login함수 호출
                login(res.data.id);
                navigate("/")
            }else{
                //로그인 실패
                alert("아이디 또는 비밀번호를 확인하세요");
            }
        })
        .catch((error)=>console.log(error));
    }

  return (
    <div id="section_wrap">
      <div className="word">로그인</div>

      <table width="500" border="1">
        <tbody>
          <tr>
            <td>아이디</td>
            <td>
              <input type="text" name="id" onChange={(e) => setId(e.target.value)} />
            </td>
          </tr>

          <tr>
            <td>비밀번호</td>
            <td>
              <input type="password" name="pw" onChange={(e) => setPw(e.target.value)} />
            </td>
          </tr>

          <tr>
            <td colSpan="2" align="center">
              <button onClick={loginHandler}>로그인</button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}