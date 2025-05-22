import React, { useState } from 'react';
import axios from 'axios';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

export default function Admin() {
  const [token, setToken] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [category, setCategory] = useState('');
  const [tags, setTags] = useState('');
  const [image, setImage] = useState(null);
  const [imageUrl, setImageUrl] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const login = async () => {
    try {
      const res = await axios.post('http://localhost:8080/api/auth/login', { username, password });
      setToken(res.data.token);
    } catch (err) {
      alert('Invalid credentials');
    }
  };

  const uploadImage = async () => {
    const formData = new FormData();
    formData.append('file', image);
    const res = await axios.post('http://localhost:8080/api/upload', formData);
    setImageUrl(res.data);
  };

  const submitPost = async () => {
    await axios.post('http://localhost:8080/api/posts', {
      title,
      content,
      category,
      tags: tags.split(',').map(t => t.trim()),
      imageUrl
    }, {
      headers: { Authorization: `Bearer ${token}` }
    });
    alert('Post submitted!');
  };

  return (
    <div>
      <h1>Blog CMS - Admin</h1>
      <input placeholder="Username" onChange={e => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" onChange={e => setPassword(e.target.value)} />
      <button onClick={login}>Login</button>
      <hr />
      <input placeholder="Title" value={title} onChange={e => setTitle(e.target.value)} />
      <input placeholder="Category" value={category} onChange={e => setCategory(e.target.value)} />
      <input placeholder="Tags (comma separated)" value={tags} onChange={e => setTags(e.target.value)} />
      <ReactQuill theme="snow" value={content} onChange={setContent} />
      <input type="file" onChange={e => setImage(e.target.files[0])} />
      <button onClick={uploadImage}>Upload Image</button>
      {imageUrl && <img src={`http://localhost:8080${imageUrl}`} alt="Preview" width="200" />}
      <br />
      <button onClick={submitPost}>Submit Post</button>
    </div>
  );
}
