// import axios from "axios";
import { useEffect, useMemo, useState } from "react";
import { signUp } from "./api";
import { Input } from "./components/input";


export function SignUp() {

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordRepeat, setPasswordRepeat] = useState("");
  const [apiProgress, setApiProgress] = useState(false);
  const [successMessage, setSuccessMessage] = useState();
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();



  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        username: undefined
      }
    })
  }, [username])

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        email: undefined
      }
    })
  }, [email])

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        password: undefined
      }
    })
  }, [password])

  const passwordRepeatError = useMemo(() => {
    if (password && password !== passwordRepeat) {
      console.log("always runnig");
      return "Password missmatch"
    }
    return "";
  }, [password, passwordRepeat])


  const onSubmit = async (event) => {
    event.preventDefault();
    setSuccessMessage();
    setGeneralError();
    setApiProgress(true);
    try {
      const response = await signUp({
        username,
        email,
        password
      })
      setSuccessMessage(response.data.message)
    } catch (axiosError) {
      if (axiosError.response?.data && axiosError.response.data.status === 400) {
        if (axiosError.response.data.validationErrors) {
          setErrors(axiosError.response.data.validationErrors);
        } else {
          console.error("Unexpected error:", axiosError);
        }
      } else {
        setGeneralError("Unexpected error occured. Pleas try again.")
      }
    } finally {
      setApiProgress(false)
    }

  }

  return (
    <div className="container mt-5">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form className="card" onSubmit={onSubmit}>
          <div className="text-center card-header">
            <h1> Sign Up </h1>
          </div>
          <div className="card-body">
            <Input id="username" label="Username" error={errors.username} onChange={(event) => setUsername(event.target.value)} />
            <Input id="email" label="E-mail" error={errors.email} onChange={(event) => setEmail(event.target.value)} />
            <Input id="password" label="Password" error={errors.password} onChange={(event) => setPassword(event.target.value)} type="password" />
            <Input id="passwordRepeat" label="Password Repeat" error={passwordRepeatError} onChange={(event) => setPasswordRepeat(event.target.value)} type="password" />


            <div>
              {successMessage && <div className="alert alert-secondary" role="alert"> {successMessage} </div>}
              {generalError && <div className="alert alert-danger" role="alert"> {generalError} </div>}
            </div>

            <button className="btn btn-primary" disabled={apiProgress || (!password || password !== passwordRepeat)}>
              {apiProgress && <span className="spinner-border spinner-border-sm" aria-hidden="true"></span>}
              Sign Up
            </button>

          </div>
        </form>
      </div>
    </div>
  )
}
