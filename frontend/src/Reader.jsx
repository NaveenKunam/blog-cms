import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Reader() {
  const [posts, setPosts] = useState([]);
  useEffect(() => {
    axios.get('http://localhost:8080/api/posts?page=0&size=10').then(res => {
      setPosts(res.data);
    });
  }, []);

  return (
    <div>
      <h1>Public Blog Reader</h1>
      {posts.map(post => (
        <div key={post.id} style={{ borderBottom: '1px solid #ccc', marginBottom: '20px' }}>
          <h2>{post.title}</h2>
          {post.imageUrl && <img src={`http://localhost:8080${post.imageUrl}`} alt="cover" width="400" />}
          <div dangerouslySetInnerHTML={{ __html: post.content }}></div>
          <p><b>Category:</b> {post.category} | <b>Tags:</b> {post.tags.join(', ')}</p>
        </div>
      ))}
    </div>
  );
}
