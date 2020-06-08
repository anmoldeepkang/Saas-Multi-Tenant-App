import React, { Component } from 'react';
import './Profile.css';
import { postNewMessage } from '../../util/APIUtils';
import { getMessagesOfUser } from '../../util/APIUtils';
import Message from '../../message/Message';
import Grid from "@material-ui/core/Grid";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/Card';
import avatar from "../../img/avatar.png";
import Link from '@material-ui/core/Link';
import { TextField } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import DialogTitle from "@material-ui/core/DialogTitle";
import Alert from 'react-s-alert';
import EditIcon from '@material-ui/icons/Edit';
import { IconButton } from '@material-ui/core';
import AddAPhotoIcon from '@material-ui/icons/AddAPhoto';
import CardMedia from '@material-ui/core/CardMedia';

class Profile extends Component {
    constructor(props) {
        super(props);
        this.state={open:false,messages:[],newPost:{}}
    }
    toggleDialog=()=>{

      this.setState({open:!this.state.open});
      if(this.state.open===false)
        this.setState({newPost:{}});
      console.log(this.state.newPost.imageUrl);
    }
    handleChange=(event)=>{
      let newPost=this.state.newPost;
      newPost.newMessage=event.target.value;
      this.setState({newPost:newPost});
    }

    addAfter=(array, index, newItem)=> {
    return [
        ...array.slice(0, index),
        newItem,
        ...array.slice(index)
    ];
    }
    handleImageUpload=(e)=>{
      e.preventDefault();

        let reader = new FileReader();
        let file = e.target.files[0];
        let newPost=this.state.newPost;
        reader.onloadend = () => {
          newPost.imageUrl=reader.result;
          newPost.imageFile=file;
          this.setState({newPost:newPost});
    }
    reader.readAsDataURL(file);
    //console.log(this.state.newPost);
  }
    handleSubmit=(event)=>{
      event.preventDefault();
      this.setState({open:false});
      let newPost=this.state.newPost;
      this.setState({newPost:{}});
      //Change the body of request to send image also
      postNewMessage({message:newPost.newMessage,image:newPost.imageUrl}).then(response=>{

        this.setState({messages:this.addAfter(this.state.messages,0,response)});

      }).catch(error => {
          Alert.error((error && error.message) || 'Oops! Message not posted. Please try again!');
      });

      this.toggleDialog();
    }
    componentDidMount() {
      getMessagesOfUser()
      .then(response => {
           this.setState({messages:response});
           console.log(response);
      }).catch(error => {
          console.error((error && error.message) || 'Oops! Something went wrong. Please try again!');
      });
    }
    render() {
      let messagesMarkup=this.state.messages.map(message=><Message key={message} message={message}/>);
      let closeImg = {cursor:'pointer', float:'right', marginTop: '5px', width: '20px'};
        return (
            <div className="profile-container">
            <Grid container spacing={1}>
              <Grid item sm={4} xs={4}>
              <Card className="card">
                <CardContent>
                  <div className="profile-info">
                  <div className="profile-avatar">
                      {
                          this.props.currentUser.imageUrl ? (
                              <img src={this.props.currentUser.imageUrl} alt={this.props.currentUser.name}/>
                          ) : (
                              <div className="text-avatar">

                                  <label for="file-input"><img src={avatar}/></label><input type="file" id="file-input" style={{display:'none'}}/>
                              </div>
                          )
                      }
                  </div>

                  <div className="profile-name">
                     <h2>{this.props.currentUser.username}</h2>
                     <p className="profile-email">{this.props.currentUser.email}</p>
                  </div>
                  </div>
                </CardContent>
              </Card>

              </Grid>
              <Grid item sm={6} xs={12}>
              <Card className="postCard">

                <CardContent>
                <div className="newPostDiv">
                    <Link
                      component="button"
                      variant="body2"
                      onClick={this.toggleDialog}
                      >
                      New Message
                      </Link>
                      <Dialog
                        open={this.state.open}
                        fullWidth
                        maxWidth="sm"
                      >

                        <DialogTitle>
                          <div>Post a new Message
                            <img src='https://d30y9cdsu7xlg0.cloudfront.net/png/53504-200.png' style={closeImg} onClick={this.toggleDialog}/>
                          </div>

                        </DialogTitle>
                        <DialogContent>


                              <TextField
                                name="body"
                                type="text"
                                label="New Message"
                                multiline
                                rows="3"
                                placeholder="Message"
                                onChange={this.handleChange}
                                fullWidth
                              />
                     <div className={(this.state.newPost.imageUrl!==undefined)?'previewImg':'dummy'}>
                      <img src={this.state.newPost.imageUrl} style={{maxWidth:'100%',maxHeight:'100%'}} />
                      </div>


                        </DialogContent>
                        <DialogActions>
                        <Grid container direction="row"
                        alignItems="center"
                        justify="center">
                          <Grid item style={{width:'86%'}}>

                          <IconButton variant="contained"  component="label"><AddAPhotoIcon/>
                            <input type="file" id="add-image" style={{display:'none'}} onChange={this.handleImageUpload}/>
                          </IconButton>
                          </Grid>
                          <Grid item style={{width:'14%'}}>
                              <Button

                                type="submit"
                                variant="contained"
                                color="primary"
                                onClick={this.handleSubmit}
                              >
                                Post

                              </Button>
                          </Grid>

                          </Grid>


                        </DialogActions>




                      </Dialog>
                </div>

                </CardContent>
              </Card>
              {messagesMarkup}

              </Grid>
            </Grid>

            </div>
        );
    }
}

export default Profile
