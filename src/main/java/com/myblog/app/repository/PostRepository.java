@PostMapping
public Post createPost(@RequestBody Post post) {
    post.setCreatedAt(java.time.LocalDateTime.now());
    post.setUpdatedAt(java.time.LocalDateTime.now());
    return postRepository.save(post);
}