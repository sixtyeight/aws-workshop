import React, {Component} from "react";
import styled from "styled-components";
import * as Cognito from "amazon-cognito-identity-js";
import jwt from "jsonwebtoken";
import logoUrl from "./logo.png";

const AppBox = styled.div`
  padding: 0.5rem;
`;

const Title = styled.div`
  display: flex;
  align-items: center;
`;

const FetcherBlock = styled.div`

`;


const FetcherArea = styled.div`
  display: flex;
`;

const Fetcher = styled.div`
  flex-grow: 1;
  width: 50%;
  padding: 0.5rem;
`;

const FetcherResponseBody = styled.textarea`
  width: 100%;
  min-height: 15rem;
  resize: none;
  margin-top: 0.5rem;
`;

const TokenBlock = styled.div`

`;

const TokenArea = styled.div`
  display: flex;
`;

const TokenViewer = styled.div`
  flex-grow: 1;
  padding: 0.5rem;
`;

const TokenData = styled.textarea`
  width: 100%;
  min-height: 15rem;
  resize: none;  
  margin-top: 0.5rem;
`;

const FailureStatus = styled.span`
  color: indianred;
`;

const SuccessStatus = styled.span`
  color: darkgreen;
`;

class App extends Component {
    state = {
        apiGatewayUrl: "",

        imagesJSONV2: "",
        imagesJSONV1: "",

        imagesStatusV2: null,
        imagesStatusV1: null,

        idTokenEncoded: "",
        idTokenDecoded: "",

        accessTokenEncoded: "",
        accessTokenDecoded: "",

        login: "",
        password: "",

        loginStatus: null,

        showAccessTokenEncoded: true,
        showIdTokenEncoded: true,
    };

    render() {
        const disabled = !this.state.apiGatewayUrl;

        return <AppBox>
            <Title>
                <img src={logoUrl} height="50" alt="" style={{marginRight: "0.5rem"}}/>
                <h1>open knowledge – BaaS Exercises</h1>
            </Title>


            <FetcherBlock>
                <h2>Images</h2>

                <label>
                    <span style={{fontWeight: "bold", marginRight: "0.5rem"}}>API Gateway URL</span>
                    <input style={{width: "25rem"}} type="text" placeholder="API Gateway URL" value={this.state.apiGatewayUrl} onChange={e => this.setState({apiGatewayUrl: e.target.value})}/>
                </label>

                <FetcherArea>
                    <Fetcher>
                        <div>
                            <button disabled={disabled} onClick={this.fetchImagesV2}>Current version (v2, <code>application/vnd.acme.v2+json</code>)</button>

                            <span style={{marginLeft: "0.5rem"}}>{this.state.imagesStatusV2}</span>
                        </div>


                        <FetcherResponseBody readOnly value={this.state.imagesJSONV2}/>
                    </Fetcher>

                    <Fetcher>
                        <div>
                            <button disabled={disabled} onClick={this.fetchImagesV1}>Old version (v1, <code>application/vnd.acme.v1+json</code>)</button>

                            <span style={{marginLeft: "0.5rem"}}>{this.state.imagesStatusV1}</span>
                        </div>

                        <FetcherResponseBody readOnly value={this.state.imagesJSONV1}/>
                    </Fetcher>
                </FetcherArea>
            </FetcherBlock>


            <hr/>

            <TokenBlock>
                <h2>Login</h2>

                <div>
                    <input type="text" placeholder={"Login"} value={this.state.login} onChange={e => this.setState({login: e.target.value})}/>
                    <input type="password" placeholder={"Password"} value={this.state.password} onChange={e => this.setState({password: e.target.value})}/>

                    <button disabled={!this.state.login} onClick={this.logIn}>Log In</button>
                    <button disabled={!this.state.idTokenEncoded} onClick={this.logOut}>Log Out</button>

                    <span style={{marginLeft: "0.5rem"}}>{this.state.loginStatus}</span>
                </div>

                <TokenArea>
                    <TokenViewer>
                        <h3>ID Token</h3>

                        <div>
                            <button onClick={() => this.setState({showIdTokenEncoded: !this.state.showIdTokenEncoded})}>
                                {this.state.showIdTokenEncoded ? "Decoded" : "Encoded"}
                            </button>
                        </div>

                        <TokenData readOnly value={this.state.showIdTokenEncoded ? this.state.idTokenEncoded : this.state.idTokenDecoded}/>
                    </TokenViewer>

                    {/*<TokenViewer>*/}
                        {/*<h3>Access Token</h3>*/}

                        {/*<div>*/}
                            {/*<button onClick={() => this.setState({showAccessTokenEncoded: !this.state.showAccessTokenEncoded})}>*/}
                                {/*{this.state.showAccessTokenEncoded ? "Decoded" : "Encoded"}*/}
                            {/*</button>*/}
                        {/*</div>*/}

                        {/*<TokenData readOnly value={this.state.showAccessTokenEncoded ? this.state.accessTokenEncoded : this.state.accessTokenDecoded}/>*/}
                    {/*</TokenViewer>*/}

                </TokenArea>
            </TokenBlock>


        </AppBox>;
    }

    getAuthorizationHeaders() {
      if (this.state.idTokenEncoded) {
        return {"Authorization": this.state.idTokenEncoded};
      }

      return {};
    }

    fetchImagesV2 = async () => {
        try {
            const options = {
                headers: {
                    ...this.getAuthorizationHeaders(),
                    "Accept": "application/vnd.acme.v2+json",
                }
            };

            const res = await fetch(`${this.state.apiGatewayUrl}/api/images`, options);

            const data = await res.json();

            const json = JSON.stringify(data, null, "   ");

            let status;

            if (res.ok) {
                status = <SuccessStatus>Status: {res.status}</SuccessStatus>;
            } else {
                status = <FailureStatus>Status: {res.status}</FailureStatus>;
            }

            this.setState({
                imagesJSONV2: json,
                imagesStatusV2: status,
            });
        } catch (e) {
            this.setState({
                imagesJSONV2: "",
                imagesStatusV2: <FailureStatus>Error: {e.message}</FailureStatus>,
            });
        }
    };

    fetchImagesV1 = async () => {
        try {
            const options = {
                headers: {
                    ...this.getAuthorizationHeaders(),
                    "Accept": "application/vnd.acme.v1+json",
                }
            };

            const res = await fetch(`${this.state.apiGatewayUrl}/api/images`, options);

            const data = await res.json();

            const json = JSON.stringify(data, null, "   ");

            let status;

            if (res.ok) {
                status = <SuccessStatus>Status: {res.status}</SuccessStatus>;
            } else {
                status = <FailureStatus>Status: {res.status}</FailureStatus>;
            }

            this.setState({
                imagesJSONV1: json,
                imagesStatusV1: status,
            });
        } catch (e) {
            this.setState({
                imagesJSONV1: "",
                imagesStatusV1: <FailureStatus>Error: {e.message}</FailureStatus>,
            });
        }
    };

    logOut = () => {
      this.setState({
          idTokenEncoded: "",
          idTokenDecoded: "",

          accessTokenEncoded: "",
          accessTokenDecoded: "",

          loginStatus: undefined,
      })
    };

    logIn = () => {
        const authenticationData = {
            Password : this.state.password,
        };

        const authenticationDetails = new Cognito.AuthenticationDetails(authenticationData);

        const poolData = {
            UserPoolId : "YOUR_USER_POOL_ID", // TODO: Your user pool id here
            ClientId : "YOUR_CLIENT_ID", // TODO: Your client id here
        };

        const userPool = new Cognito.CognitoUserPool(poolData);

        const userData = {
            Username : this.state.login,
            Pool : userPool,
        };

        const cognitoUser = new Cognito.CognitoUser(userData);

        const callbacks = {
            newPasswordRequired: () => {
                // Make sure we always set password, given name and family name.
                cognitoUser.completeNewPasswordChallenge("mustermann", {
                    "given_name": "Max",
                    "family_name": "Mustermann",
                }, callbacks);
            },

            onSuccess:  result => {
                const idToken = result.getIdToken().getJwtToken();
                const accessToken = result.getAccessToken().getJwtToken();

                this.setState({
                    idTokenEncoded: idToken,
                    idTokenDecoded: JSON.stringify(jwt.decode(idToken), null, "   "),

                    accessTokenEncoded: accessToken,
                    accessTokenDecoded: JSON.stringify(jwt.decode(accessToken), null, "   "),
                });

                this.setState({loginStatus: <SuccessStatus>Login successful</SuccessStatus>});
            },

            onFailure: err => {
                this.setState({loginStatus: <FailureStatus>Error: {err.message}</FailureStatus>});
            },
        };

        cognitoUser.authenticateUser(authenticationDetails, callbacks);
    }
}

export default App