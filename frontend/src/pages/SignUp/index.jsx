import { useState } from "react";
import { signUp } from "./api";


export function SignUp() {

  const [username, setUsername] = useState()
  const [email, setEmail] = useState()
  const [password, setPassword] = useState()
  const [passwordRepeat, setPasswordRepeat] = useState()
  const [apiProgress, setApiProgress] = useState(false); // baslangic durumunda false
  const [successMessage, setSuccessMessage] = useState(); // kullaniya mesaj gostermek icin


  const onSubmit = async (event) => {
    event.preventDefault();
    setApiProgress(true); // form submit edildiginde bu trueya cekildi.
    try {
      const response = await signUp({
        username,
        email,
        password
      })
      setSuccessMessage(response.data.message)
    } catch (error) {
      console.log('i will continue')
    } finally {
      setApiProgress(false)
    }

  }

  return (
    <div className="container mt-5">
    <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
      <form className="card"  onSubmit={onSubmit}>
        <div className="text-center card-header">
          <h1> Sign Up </h1>
        </div>
        <div className="card-body">
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Username: </label>
            <input id="username" className="form-control" onChange={(event) => setUsername(event.target.value)}  />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">E-mail: </label>
            <input id="email" className="form-control" onChange={(event) => setEmail(event.target.value)} />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password: </label>
            <input id="password" className="form-control" type="password" onChange={(event) => setPassword(event.target.value)} />
          </div>
          <div className="mb-3">
            <label htmlFor="passwordRepeat" className="form-label">Password Repeat: </label>
            <input id="passwordRepeat" className="form-control" type="password" onChange={(event) => setPasswordRepeat(event.target.value)} />
          </div>
          <div>
            {successMessage && <div className="alert alert-secondary" role="alert"> {successMessage} </div>}
            {/* kullanilci yaratilmissa msj gostermesi icin. successmessage var ise goster. backenden gelen responeseyi isleyip gosteriyoruz. */}
          </div>
          <button className="btn btn-primary" disabled={apiProgress || (!password || password !== passwordRepeat || !username || !email)}>
              {apiProgress && <span className="spinner-border spinner-border-sm" aria-hidden="true"></span>}
              {/* Ekrana loading gozukmesi icin yaptik. api progres true ise sag blok calisicak anlamina geliyor. */}
            Sign Up
          </button>
          {/* butona ekledikform submit olursa disadble olsun diye */}
        </div>
      </form>
    </div>
  </div>
  )
}
